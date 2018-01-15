import React from 'react';
import {Text, View, Button, StyleSheet, TextInput, Linking, Picker, Alert, AsyncStorage} from 'react-native';

import { Database } from 'react-native-database';
import PokemonRestApi from './restapi.js';
import { Settings } from 'react-native-database';

class Pokemon {};
Pokemon.schema = {
  name: 'Pokemon',
  primaryKey: 'id',
  properties: {
    id: { type: 'int' },
    name: { type: 'string' },
    role: { type: 'string' },
    type: { type: 'string' },
  },
};
const schema = { schema: [Pokemon], schemaVersion: 1 };
const database = new Database(schema);
const settings = new Settings(database);
const api = new PokemonRestApi();


export default class DetailScreen extends React.Component {
    constructor(props){
        super(props)
        const {params} = this.props.navigation.state;
        this.state = {
            id   : params.datasource._dataBlob.s1[params.rowID].id,
            name : params.datasource._dataBlob.s1[params.rowID].name, 
            type : params.datasource._dataBlob.s1[params.rowID].type,
            role : params.datasource._dataBlob.s1[params.rowID].role,
        }
    }

    _onPressSave(){
        const {params} = this.props.navigation.state;
        database.write(() => {
            var newArray= [];
            newArray = params.datasource._dataBlob.s1.slice()
            newArray[params.rowID].name = this.state.name;
            newArray[params.rowID].type = this.state.type;
            newArray[params.rowID].role = this.state.role;
        })
        jsonString = JSON.stringify({
            id: this.state.id,
            name:this.state.name, 
            type:this.state.type, 
            role: this.state.role
        })
        console.log(jsonString);
        api.updatePokemon(jsonString, this.state.id);
        this.props.navigation.navigate('List', {})
    }

    async _onPressDelete(){
        const {params} = this.props.navigation.state;
        console.log("DELETEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE")
        const rights = await AsyncStorage.getItem("rank");
        if(rights == "regular")
        {
            Alert.alert('Warning',
                        'You do not have the permission to do that',
                    [
                        
                    ])
        }
        else
        {
            Alert.alert(
                'Warning',
                'Are you sure you want to delete this?',
                [
                  {text: 'Yes', onPress: () =>
                   {database.write(() => {
                    let stuff = database.objects('Pokemon');
                    let pokemon = stuff.filtered('id=' + this.state.id);
                    console.log("State id: " + this.state.id);
                    console.log("Pokemon result: " + JSON.stringify(pokemon[0], null, '\t'));
                    //var newArray= [];
                    //newArray = params.datasource._dataBlob.s1.slice()
                    database.delete("Pokemon", pokemon);
                    })
                    
                    api.deletePokemon(this.state.id);
                    this.props.navigation.navigate('List', {})
                 }},
                  {text: 'Cancel', onPress: () => console.log('Cancel Pressed'), style: 'cancel'},
                ],
                { cancelable: true }
              )
        }
        
        
    }

    _onPressShare(){
        const {params} = this.props.navigation.state;
        database.write(() => {
        var newArray= [];
        newArray = params.datasource._dataBlob.s1.slice()
        newArray[params.rowID].name = this.state.name;
        newArray[params.rowID].type = this.state.type;
        newArray[params.rowID].role = this.state.role;
        Linking.openURL('mailto:galstefana@gmail.com?subject=activity_log&body=' + this.state.name + this.state.shareMessage)
        })
    }

    render() {
        const {params} = this.props.navigation.state;
        console.log(JSON.stringify(params, null, '\t'));
        return (
            <View style={styles.container}>
                <Text> Pokemon name: </Text>
                <TextInput
                    style={{height: 40, width: 300, borderColor: 'gray', borderWidth: 1}}
                    value={this.state.name}
                    onChangeText={(name) => {
                        this.setState({name});  
                    }}
                />

                <TextInput
                    style={{height: 40, width: 300, borderColor: 'gray', borderWidth: 1}}
                    value={this.state.type}
                    onChangeText={(type) => {
                        this.setState({type});  
                    }}
                />

                <TextInput
                    style={{height: 40, width: 300, borderColor: 'gray', borderWidth: 1}}
                    value={this.state.role}
                    onChangeText={(role) => {
                        this.setState({role});  
                    }}
                />
                
                <Button onPress = {()=>this._onPressSave()} title = "save"/>
                <Button onPress = {()=>this._onPressShare()} title = "share"/>
                <Button onPress = {()=>this._onPressDelete()} title = "delete"/>
                <Picker
                    selectedValue={this.state.shareMessage}
                    onValueChange={(itemValue, itemIndex) => this.setState({shareMessage: itemValue})}>
                    <Picker.Item label="Battle" value="battle" />
                    <Picker.Item label="Trial" value="trial" />
                </Picker>
            </View>
        );
    }

    
    rowPressed(data, text) {
        let newArray=this.state.dataSource._dataBlob.s1.slice()
        newArray[0] = {
        ...this.state.dataSource[0],
        name:text
        }
        console.log(JSON.stringify(this.state.dataSource, null, '\t'));
        this.setState({dataSource: ds.cloneWithRows(newArray)});
        console.log(JSON.stringify(this.state.dataSource, null, '\t'));
    }
};


var styles = StyleSheet.create({
    container:{
        flex:1,
        padding: 10,
        paddingTop:70,
        },
    });