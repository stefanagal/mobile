

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
import LoginScreen from './login.js';
import DetailScreen from './detail.js';
import CreateScreen from './create.js';
import BarChartColumnBasic from './BarChartColumnBasic.js';
/*
const MainScreenNavigator = TabNavigator({
  List: {screen: ListScreen},
});
*/
const MainScreenNavigator = TabNavigator({
  Login: {screen: LoginScreen}
})

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
  Create: {
    screen:CreateScreen
  },
  List: {
    screen:ListScreen
  },
  BarChartColumnBasic: { screen: BarChartColumnBasic },
  
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
