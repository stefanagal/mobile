
import React from 'react';


export default class PokemonRestApi extends React.Component{
    constructor(){
        super();
        this.state = { "url" : "http://10.0.2.2:3000"}
    }

    authenticateUser(username, password){
        console.log('login request')
        console.log(this.state.url + "/users?username=" + username + "&password=" + password);
        return fetch(this.state.url + "/users?username=" + username + "&password=" + password,
        {
            method: 'GET',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
              },
        })
        .then(result => result.json())
        
    }

    getPokemon(){
        return fetch(this.state.url + "/pokemon")
        .then(result => result.json())
    }

    addPokemon(data)
    {
        return fetch(this.state.url + "/pokemon",
    {
        method : "POST",
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
          },
        body: data
    })
    }

    updatePokemon(data, id)
    {
        return fetch(this.state.url + "/pokemon/" + id,
    {
        method: "PUT",
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
          },
        body: data
    })
    }

    deletePokemon(id)
    {
        return fetch(this.state.url + "/pokemon/" + id,
    {
        method : "DELETE",
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json',
          },
    })
    }
}