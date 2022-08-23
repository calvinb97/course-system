import React, { useEffect, useState } from 'react';
import { SelectUserButton } from './SelectUserButton';

export const UsersList = (props) => {

    const [students, setStudents] = useState([]);
    useEffect(() => {
        fetch("http://localhost:4567/students")
        .then(response => response.json())
        .then(data => setStudents(data))
        .catch(err => console.log(err.message));
    }, []);


    return(
        <div>
            <table>
                <tr>
                    <th>Nachname</th>
                    <th>Vorname</th>
                    <th></th>
                </tr>
            {students.map(student => {
                return (
                    <tr>
                        <td>{student.lastName}</td>
                        <td>{student.firstName}</td>
                        <td><SelectUserButton id={student.id} onClick={props.handleButton} /></td>
                    </tr>
                );
            })}
            </table>
        </div>
    );
};