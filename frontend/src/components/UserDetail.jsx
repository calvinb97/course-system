import React, { useEffect, useState } from 'react';
import { UserEnrolments } from './UserEnrolments';

export const UserDetail = (props) => {
    
    const [currentStudent, setCurrentStudent] = useState({});
    useEffect(() => {
        if(props.id){
            fetch("http://localhost:4567/students/" + props.id)
            .then(response => response.json())
            .then(data => setCurrentStudent(data))
            .catch(err => console.log(err.message));
        }
    }, []);

    return(
        <div>
            <h1>Student: {currentStudent.firstName} {currentStudent.lastName}</h1>
            <table>
                <tr>
                    <td>ID</td>
                    <td>{currentStudent.id}</td>
                </tr>
                <tr>
                    <td>Nachname</td>
                    <td>{currentStudent.lastName}</td>
                </tr>
                <tr>
                    <td>Vorname</td>
                    <td>{currentStudent.firstName}</td>
                </tr>
            </table>
            <h2>Kursbelegung</h2>
            <UserEnrolments id={props.id} />
        </div>
    );
}