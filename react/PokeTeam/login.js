import React from 'react';
import {
  View,
  ListView,
  StyleSheet,
  Navigator,
  TouchableOpacity,
  Text,
  TextInput,
  Button,
  AsyncStorage
} from 'react-native';

import { Database } from 'react-native-database';
import { Settings } from 'react-native-database';

import PokemonRestApi from './restapi.js';


const api = new PokemonRestApi();

export default class LoginScreen extends React.Component
{
    constructor() 
    {
        super();
        this.state = 
        {
            username : "admin",
            password : "admin",
            success: false
        }
    }

    _onPress()
    {
        api.authenticateUser(this.state.username, this.state.password)
        .then(function(data)
        {
          logins = data;
        })
        .then(async () => 
        {
        var count = Object.keys(logins).length;
        if (count == 1) {
            await AsyncStorage.setItem('isLoggedIn', "true");
            await AsyncStorage.setItem('rank', logins[0]["rank"]);
            this.setState({ success: true });
            this.props.navigation.navigate('List', {});
        } else {
            this.setState({ success: false });
        }
        })
        .catch((error) =>
        {
        console.log('login problem');
        });
        //this.props.navigation.navigate('List', {})
    }

    render() 
    {
        return (
            <View>
                <Text>Login</Text>

                <Text>Username: </Text>
                <TextInput
                    style={{height: 40, width: 300, borderColor: 'gray', borderWidth: 1}}
                    value={this.state.username}
                    onChangeText={(username) => {
                        this.setState({username});  
                    }}
                />

                <Text>Password:</Text>
                <TextInput
                    style={{height: 40, width: 300, borderColor: 'gray', borderWidth: 1}}
                    value={this.state.password}   
                    secureTextEntry={true}       
                    onChangeText={(password) => {
                        this.setState({password});  
                    }}
                />
                <Button onPress = {()=>this._onPress()} title = "Log in"/>
            </View>
        );
    }
}