import React from 'react';

export const SelectUserButton = (props) => {
    return(
        <button id={props.id} onClick={(e) => {props.onClick(props.id, e)}}>Auswählen</button>
    );
}