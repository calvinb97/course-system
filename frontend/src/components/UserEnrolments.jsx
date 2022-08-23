import React, { useState, useEffect } from 'react';


export const UserEnrolments = (props) => {

    const [enrolments, setEnrolments] = useState([]);
    useEffect(() => {
        fetch("http://localhost:4567/students/" + props.id + "/enrolments" )
        .then(response => response.json())
        .then(data => setEnrolments(data))
        .catch(err => console.log(err.message));
    }, []);

    if(enrolments.length === 0){
        return("Noch keine Kurse belegt.");
    }

    return(
        <table>
            <tr>
                <th>Kurs</th>
                <th>Note</th>
            </tr>
            {enrolments.map(enrolment => {
                return(
                    <tr>
                        <td>{enrolment.courseName}</td>
                        <td>{enrolment.grade !== 0 ? enrolment.grade : "Noch keine Note"}</td>
                    </tr>
                );
            })}
        </table>
    );
}