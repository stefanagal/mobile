import React from 'react';
import {Text, View, Button, StyleSheet, TextInput, Linking} from 'react-native';



export default class DetailScreen extends React.Component {
    constructor(props){
        super(props)
        const {params} = this.props.navigation.state;
        this.state = {
            name : params.datasource._dataBlob.s1[params.rowID].name, 
            type : params.datasource._dataBlob.s1[params.rowID].type,
            role : params.datasource._dataBlob.s1[params.rowID].role,
        }
    }

    _onPressSave(){
        const {params} = this.props.navigation.state;
        var newArray= [];
        newArray = params.datasource._dataBlob.s1.slice()
        newArray[params.rowID].name = this.state.name;
        newArray[params.rowID].type = this.state.type;
        newArray[params.rowID].role = this.state.role;
    }

    _onPressShare(){
        const {params} = this.props.navigation.state;
        var newArray= [];
        newArray = params.datasource._dataBlob.s1.slice()
        newArray[params.rowID].name = this.state.name;
        newArray[params.rowID].type = this.state.type;
        newArray[params.rowID].role = this.state.role;
        Linking.openURL('mailto:teopaval@gmail.com?subject=activity_log&body=' + this.state.name)
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