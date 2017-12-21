import React, { Component } from 'react';
import { View, Text, StyleSheet } from 'react-native';

import { Bar } from 'react-native-pathjs-charts'

import { Database } from 'react-native-database';
import { Settings } from 'react-native-database';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#f7f7f7',
  },
});

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

class BarChartColumnBasic extends Component {
  static navigationOptions = ({ navigation }) => ({
    title: `Pokemon usage by types`,
  });
  render() {
    let data = [
      [{
        "v": 2,
        "name": "poison"
      }, {
        "v": 5,
        "name": "grass"
      },
      {
        "v": 6,
        "name": "water"
      }]
    ]

    let options = {
      width: 300,
      height: 300,
      margin: {
        top: 20,
        left: 25,
        bottom: 50,
        right: 20
      },
      color: '#2980B9',
      gutter: 20,
      animate: {
        type: 'oneByOne',
        duration: 200,
        fillTransition: 3
      },
      axisX: {
        showAxis: true,
        showLines: true,
        showLabels: true,
        showTicks: true,
        zeroAxis: false,
        orient: 'bottom',
        label: {
          fontFamily: 'Arial',
          fontSize: 8,
          fontWeight: true,
          fill: '#34495E',
          rotate: 45
        }
      },
      axisY: {
        showAxis: true,
        showLines: true,
        showLabels: true,
        showTicks: true,
        zeroAxis: false,
        orient: 'left',
        label: {
          fontFamily: 'Arial',
          fontSize: 8,
          fontWeight: true,
          fill: '#34495E'
        }
      }
    }

    return (
      <View style={styles.container}>
        <Bar data={data} options={options} accessorKey='v'/>
      </View>
    )
  }
}

export default BarChartColumnBasic;