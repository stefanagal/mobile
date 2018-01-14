import React from 'react';
import {
  View,
  ListView,
  StyleSheet,
  Navigator,
  TouchableOpacity,
  Text,
  Button,
  AsyncStorage
} from 'react-native';

import { Database } from 'react-native-database';
import { Settings } from 'react-native-database';

import PokemonRestApi from './restapi.js';

const database_name = "poketeam.db";
const database_version = "1.0";
const database_displayname = "PokeTeam Database";
const database_size = 200000;
const api = new PokemonRestApi();
let db;

var DomParser = require('xmldom').DOMParser;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginTop: 80,
  },
  separator: {
    flex: 1,
    height: StyleSheet.hairlineWidth,
    backgroundColor: '#8E8E8E',
  },
  progress: {
    marginTop: 80,
  },
});

const ds = new ListView.DataSource({
    rowHasChanged: (r1, r2) => r1.name !== r2.name
    
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

export const rows = [
  {
    name:'bulbasaur', 
    type:'grass/poison', 
    //moves:['tackle','tail whip','leech seed', 'poison powder'],
    role: 'stall',
  },
  {
    name:'squirtle', 
    type:'water',
    //moves:['harden','bubble','water gun','scratch'],
    role:'special attacker',
  }]
    
class ListScreen extends React.Component {
  constructor(props){
    super(props);

    this.state = {
      progress: [],
      dataSource: ds.cloneWithRows(database.objects('Pokemon')),
      loading: false
    };
    

    
    //console.log(JSON.stringify(this.state.dataSource, null, '\t'));
    //this.state.dataSource = this.state.dataSource.bind(this);
    
  }

  errorCB(err) {
    console.log("error: ", err);
    this.state.progress.push("Error " + (err.message || err));
    this.setState(this.state);
  }

  _onRefresh() {
    var newData = [];
    newData = rows.slice();
    this.setState({dataSource:ds.cloneWithRows(database.objects('Pokemon'))})
  }

  _onPressRow(data, sectionID, rowID){
    this.props.navigation.navigate('Details', {data:data, rowID:rowID, datasource:this.state.dataSource})
    this.setState({dataSource:ds.cloneWithRows(database.objects('Pokemon'))})
    
  }

  _onPressCreate(){
    this.props.navigation.navigate('Create')
    //this.setState({dataSource:ds.cloneWithRows(database.objects('Pokemon'))})
    
  }

  startLoading(){
    return Promise.all(() => {this.loadAndQueryDB()});
  }

  loadAndQueryDB() {
    
    
    console.log("starting load");

    
    database.write(() => {
      database.deleteAll(); 
      
    });
    
      api.getPokemon()
      .then(data => {
        data.map(element => 
          {           
            database.write(() => {
              database.create('Pokemon',
            {
              id: element["id"],
              name: element["name"],
              type: element["type"],
              role: element["role"]
            }
            )
          })});
        })
      .then(data => {
      this.setState({loading: false })
    });
    


  }

  componentDidMount(){
    this.setState({loading: true })
    //this.state.dataSource = ds.cloneWithRows(database.objects('Pokemon'))
    this.loadAndQueryDB();

  }


  renderProgressEntry(entry) {
    return (<View style={styles.container}>
      <View>
        <Text >{entry}</Text>
      </View>
    </View>)
  }

  render(){
    var crtds = new ListView.DataSource({
      rowHasChanged: (row1, row2) => {
        row1 !== row2;
      }
    });
    this.startLoading().then(() => {
      
    })
    if(this.state.loading)
    {
    return (<View>
      <Text>Loading</Text>
    </View>);
    }
    return (
      <View style={styles.container}>
        <View>
          <Button title="Create" style={styles.toolbarButton} onPress={() => this._onPressCreate()}/>
            
          <Button
          
          onPress={() => this.props.navigation.navigate('BarChartColumnBasic')}
          title="Pokemon Usage"
          />
          
        </View>
        <ListView
          enableEmptySections={true}
          dataSource={this.state.dataSource}
          
          renderRow={(data, sectionID, rowID) => 
            <TouchableOpacity onPress={()=> this._onPressRow(data, sectionID, rowID)}>
               <View>
                 <Text style={styles.symbol}>{data.name} - {data.type}</Text> 
               </View>
            </TouchableOpacity>
            }
          renderSeparator={(sectionID, rowID, adjacentRowHighlighted) =>
            <View key={rowID} style={{height:1, backgroundColor: 'lightgray'}}/>
          }
          
         />
         
      </View>
    );

  }
}

export default ListScreen;