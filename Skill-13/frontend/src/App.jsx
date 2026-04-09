import { useState, useEffect } from 'react'
import axios from 'axios'
import StudentList from './components/StudentList'
import AddStudent from './components/AddStudent'
import './App.css'

const API_URL = import.meta.env.VITE_API_URL || '/students'

function App() {
  const [students, setStudents] = useState([])
  const [editingStudent, setEditingStudent] = useState(null)

  // Fetch all students
  const fetchStudents = async () => {
    try {
      const response = await axios.get(API_URL)
      setStudents(response.data)
    } catch (error) {
      console.error('Error fetching students:', error)
    }
  }

  useEffect(() => {
    fetchStudents()
  }, [])

  // Add a new student
  const addStudent = async (student) => {
    try {
      await axios.post(API_URL, student)
      fetchStudents()
    } catch (error) {
      console.error('Error adding student:', error)
    }
  }

  // Update an existing student
  const updateStudent = async (id, student) => {
    try {
      await axios.put(`${API_URL}/${id}`, student)
      setEditingStudent(null)
      fetchStudents()
    } catch (error) {
      console.error('Error updating student:', error)
    }
  }

  // Delete a student
  const deleteStudent = async (id) => {
    try {
      await axios.delete(`${API_URL}/${id}`)
      fetchStudents()
    } catch (error) {
      console.error('Error deleting student:', error)
    }
  }

  // Set student to edit
  const handleEdit = (student) => {
    setEditingStudent(student)
  }

  // Cancel editing
  const handleCancelEdit = () => {
    setEditingStudent(null)
  }

  return (
    <div className="app">
      <header className="app-header">
        <div className="header-content">
          <h1>📚 Student Management System</h1>
          <p>Manage your student records with ease</p>
        </div>
      </header>

      <main className="app-main">
        <AddStudent
          onAdd={addStudent}
          onUpdate={updateStudent}
          editingStudent={editingStudent}
          onCancelEdit={handleCancelEdit}
        />

        <StudentList
          students={students}
          onEdit={handleEdit}
          onDelete={deleteStudent}
        />
      </main>

      <footer className="app-footer">
        <p>Student Management System &copy; 2026</p>
      </footer>
    </div>
  )
}

export default App
