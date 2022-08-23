import React, { useState } from 'react';
import { UserSelector } from './UserSelector';
import { UserDetail } from './UserDetail';
import { UsersList } from './UsersList';

export const UserContainer = () => {

    const [currentStudentId, setCurrentStudentId] = useState(null);

    const handleChange = (e) => {
        console.log("change");
        setCurrentStudentId(2);
    }

    const handleButton = (id, e) => {
        console.log("button pressed");
        setCurrentStudentId(id);
    }

    const handleShowUsersButton = (e) => {
        setCurrentStudentId(null);
    }

    return (
        <div>
            <UserSelector onChange={handleChange} onClick={handleShowUsersButton} currentId={currentStudentId} />
            {!currentStudentId ? <UsersList handleButton={handleButton} /> : <UserDetail id={currentStudentId} />}
        </div>

    );

}