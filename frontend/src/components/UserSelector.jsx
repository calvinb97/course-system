import React from 'react';

export const UserSelector = (props) => {
    return(
        <div>
            <h2>Student ID:</h2>
            <input type="text" id="user-selector" onChange={props.onChange} placeholder={props.currentId ? props.currentId : "Student ID"}></input>
            <button id="show-users" onClick={props.onClick}>Übersicht</button> 
        </div>
    );
}