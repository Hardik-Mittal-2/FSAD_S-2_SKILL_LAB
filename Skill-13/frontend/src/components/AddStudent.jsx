import { useState, useEffect } from 'react'

function AddStudent({ onAdd, onUpdate, editingStudent, onCancelEdit }) {
  const [name, setName] = useState('')
  const [email, setEmail] = useState('')
  const [course, setCourse] = useState('')

  // Prefill form when editing
  useEffect(() => {
    if (editingStudent) {
      setName(editingStudent.name)
      setEmail(editingStudent.email)
      setCourse(editingStudent.course)
    } else {
      setName('')
      setEmail('')
      setCourse('')
    }
  }, [editingStudent])

  const handleSubmit = (e) => {
    e.preventDefault()

    if (!name.trim() || !email.trim() || !course.trim()) {
      alert('Please fill in all fields')
      return
    }

    const studentData = { name, email, course }

    if (editingStudent) {
      onUpdate(editingStudent.id, studentData)
    } else {
      onAdd(studentData)
    }

    // Clear form
    setName('')
    setEmail('')
    setCourse('')
  }

  return (
    <div className="form-card">
      <h2>{editingStudent ? '✏️ Update Student' : '➕ Add New Student'}</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="name">Name</label>
          <input
            id="name"
            type="text"
            placeholder="Enter student name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </div>
        <div className="form-group">
          <label htmlFor="email">Email</label>
          <input
            id="email"
            type="email"
            placeholder="Enter student email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div className="form-group">
          <label htmlFor="course">Course</label>
          <input
            id="course"
            type="text"
            placeholder="Enter course name"
            value={course}
            onChange={(e) => setCourse(e.target.value)}
          />
        </div>
        <div className="form-actions">
          <button type="submit" className={editingStudent ? 'btn-update' : 'btn-add'}>
            {editingStudent ? 'Update Student' : 'Add Student'}
          </button>
          {editingStudent && (
            <button type="button" className="btn-cancel" onClick={onCancelEdit}>
              Cancel
            </button>
          )}
        </div>
      </form>
    </div>
  )
}

export default AddStudent
