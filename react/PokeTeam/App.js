

import React from 'react';
import {
    StyleSheet,
    Text,
    View,
    ListView,
    ActivityIndicator,
    Button
} from 'react-native';

import {
	StackNavigator,
} from 'react-navigation';

import {TabNavigator} from "react-navigation";

import ListScreen from './pokemonlist.js';
import DetailScreen from './detail.js';

const MainScreenNavigator = TabNavigator({
  List: {screen: ListScreen},
});


const SimpleApp = StackNavigator({
  Home: {
    screen: MainScreenNavigator,
    navigationOptions: {
      title: 'Home',
    },
  },
  Details: {
    screen:DetailScreen
  },
  
});


export default class App extends React.Component {
  render() {
    return <SimpleApp/>;
  }
}


const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
