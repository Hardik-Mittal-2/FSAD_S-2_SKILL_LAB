import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { 
    Table, TableBody, TableCell, TableContainer, 
    TableHead, TableRow, Paper, Button, Typography, Box
} from '@mui/material';

const StudentList = ({ refreshTrigger, onEdit }) => {
    const [students, setStudents] = useState([]);

    useEffect(() => {
        fetchStudents();
    }, [refreshTrigger]);

    const fetchStudents = async () => {
        try {
            const response = await axios.get('http://localhost:8080/students');
            setStudents(response.data);
        } catch (error) {
            console.error('Error fetching students:', error);
        }
    };

    const handleDelete = async (id) => {
        try {
            await axios.delete(`http://localhost:8080/students/${id}`);
            fetchStudents(); // Ensure the StudentList updates immediately
        } catch (error) {
            console.error('Error deleting student:', error);
        }
    };

    return (
        <Box>
            <Typography variant="h5" gutterBottom fontWeight="bold" color="text.secondary">
                Registered Students
            </Typography>
            <TableContainer component={Paper} elevation={3} sx={{ borderRadius: 2, overflow: 'hidden' }}>
                <Table>
                    <TableHead sx={{ bgcolor: 'secondary.main' }}>
                        <TableRow>
                            <TableCell sx={{ color: 'white', fontWeight: 'bold' }}>ID</TableCell>
                            <TableCell sx={{ color: 'white', fontWeight: 'bold' }}>Name</TableCell>
                            <TableCell sx={{ color: 'white', fontWeight: 'bold' }}>Email</TableCell>
                            <TableCell sx={{ color: 'white', fontWeight: 'bold' }}>Course</TableCell>
                            <TableCell align="center" sx={{ color: 'white', fontWeight: 'bold' }}>Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {students.map((student) => (
                            <TableRow key={student.id} hover>
                                <TableCell>{student.id}</TableCell>
                                <TableCell>{student.name}</TableCell>
                                <TableCell>{student.email}</TableCell>
                                <TableCell>{student.course}</TableCell>
                                <TableCell align="center">
                                    <Button 
                                        variant="outlined" 
                                        color="primary" 
                                        size="small" 
                                        onClick={() => onEdit(student)} 
                                        sx={{ mr: 1 }}
                                    >
                                        Update
                                    </Button>
                                    <Button 
                                        variant="outlined" 
                                        color="error" 
                                        size="small" 
                                        onClick={() => handleDelete(student.id)}
                                    >
                                        Delete
                                    </Button>
                                </TableCell>
                            </TableRow>
                        ))}
                        {students.length === 0 && (
                            <TableRow>
                                <TableCell colSpan={5} align="center" sx={{ py: 3, color: 'text.secondary' }}>
                                    No students found. Add a student to get started.
                                </TableCell>
                            </TableRow>
                        )}
                    </TableBody>
                </Table>
            </TableContainer>
        </Box>
    );
};

export default StudentList;
