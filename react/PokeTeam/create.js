import React from 'react';
import {Text, View, Button, StyleSheet, TextInput, Linking} from 'react-native';

import { Database } from 'react-native-database';
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


export default class CreateScreen extends React.Component {
    constructor(props){
        super(props)
        const {params} = this.props.navigation.state;
        this.state = {
            name : "", 
            type : "",
            role : "",
        }
    }

    _onPressSave(){
        const {params} = this.props.navigation.state;
        database.write(() => {
            let pokeList = database.objects('Pokemon').sorted("id", true);
            var lastid = 0;
            if(pokeList.length != 0)
                lastid = pokeList[0].id;
            database.create('Pokemon', 
            {
              id:lastid + 1,
              name:this.state.name, 
              type:this.state.type, 
              role: this.state.role,
            })
        })
        this.props.navigation.navigate('Home', {})
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

            </View>
        );
    }
};


var styles = StyleSheet.create({
    container:{
        flex:1,
        padding: 10,
        paddingTop:70,
        },
    });