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
      dataSource: ds.cloneWithRows(rows),
    };

    console.log(JSON.stringify(this.state.dataSource, null, '\t'));
    //this.state.dataSource = this.state.dataSource.bind(this);
  }

  _onPressRow(data, sectionID, rowID){
    this.props.navigation.navigate('Details', {data:data, rowID:rowID, datasource:this.state.dataSource})
    var newData = [];
    newData = rows.slice();
    this.setState({dataSource:ds.cloneWithRows(newData)})
    
  }
  render(){
    return (
      	<ListView style={styles.container}
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
    );
  }
}

export default ListScreen;