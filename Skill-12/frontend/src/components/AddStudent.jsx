import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { TextField, Button, Box, Typography, Paper } from '@mui/material';

const AddStudent = ({ onStudentAdded, studentToEdit, onStudentUpdated }) => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [course, setCourse] = useState('');

    useEffect(() => {
        if (studentToEdit) {
            setName(studentToEdit.name);
            setEmail(studentToEdit.email);
            setCourse(studentToEdit.course);
        } else {
            setName('');
            setEmail('');
            setCourse('');
        }
    }, [studentToEdit]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const student = { name, email, course };
        try {
            if (studentToEdit) {
                await axios.put(`http://localhost:8080/students/${studentToEdit.id}`, student);
                onStudentUpdated();
            } else {
                await axios.post('http://localhost:8080/students', student);
                onStudentAdded();
            }
            setName('');
            setEmail('');
            setCourse('');
        } catch (error) {
            console.error('Error saving student:', error);
        }
    };

    return (
        <Paper elevation={3} sx={{ p: 4, borderRadius: 2 }}>
            <Typography variant="h5" gutterBottom fontWeight="bold" color="text.secondary">
                {studentToEdit ? 'Edit Student Details' : 'Add New Student'}
            </Typography>
            <Box component="form" onSubmit={handleSubmit} sx={{ display: 'flex', gap: 2, alignItems: 'center', flexWrap: 'wrap', mt: 2 }}>
                <TextField 
                    label="Name" 
                    variant="outlined" 
                    value={name} 
                    onChange={(e) => setName(e.target.value)} 
                    required 
                    size="small"
                    sx={{ flexGrow: 1 }}
                />
                <TextField 
                    label="Email" 
                    variant="outlined" 
                    type="email" 
                    value={email} 
                    onChange={(e) => setEmail(e.target.value)} 
                    required 
                    size="small"
                    sx={{ flexGrow: 1 }}
                />
                <TextField 
                    label="Course" 
                    variant="outlined" 
                    value={course} 
                    onChange={(e) => setCourse(e.target.value)} 
                    required 
                    size="small"
                    sx={{ flexGrow: 1 }}
                />
                <Button type="submit" variant="contained" color={studentToEdit ? "secondary" : "primary"} sx={{ minWidth: '120px' }}>
                    {studentToEdit ? 'Update' : 'Add Student'}
                </Button>
            </Box>
        </Paper>
    );
};

export default AddStudent;
