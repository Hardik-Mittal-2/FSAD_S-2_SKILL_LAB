import React, { useState } from 'react';
import StudentList from './components/StudentList';
import AddStudent from './components/AddStudent';
import { Container, Typography, Divider, Box, CssBaseline } from '@mui/material';

function App() {
  const [refreshTrigger, setRefreshTrigger] = useState(0);
  const [studentToEdit, setStudentToEdit] = useState(null);

  const handleStudentAdded = () => {
    setRefreshTrigger(prev => prev + 1);
  };

  const handleStudentUpdated = () => {
    setStudentToEdit(null);
    setRefreshTrigger(prev => prev + 1);
  };

  const handleEdit = (student) => {
    setStudentToEdit(student);
  };

  return (
    <Container maxWidth="md" sx={{ mt: 4, mb: 4 }}>
      <CssBaseline />
      <Typography variant="h3" align="center" gutterBottom color="primary.main" fontWeight="bold">
        Student Management System
      </Typography>
      <Box sx={{ mb: 4 }}>
        <AddStudent 
          onStudentAdded={handleStudentAdded} 
          studentToEdit={studentToEdit} 
          onStudentUpdated={handleStudentUpdated} 
        />
      </Box>
      <Divider sx={{ my: 4 }} />
      <Box>
        <StudentList 
          refreshTrigger={refreshTrigger} 
          onEdit={handleEdit} 
        />
      </Box>
    </Container>
  );
}

export default App;
