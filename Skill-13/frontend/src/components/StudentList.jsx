function StudentList({ students, onEdit, onDelete }) {
  if (students.length === 0) {
    return (
      <div className="list-card">
        <h2>📋 Student Records</h2>
        <div className="empty-state">
          <p>No students found. Add a student to get started!</p>
        </div>
      </div>
    )
  }

  return (
    <div className="list-card">
      <h2>📋 Student Records <span className="badge">{students.length}</span></h2>
      <div className="table-wrapper">
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Email</th>
              <th>Course</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {students.map((student) => (
              <tr key={student.id}>
                <td>{student.id}</td>
                <td>{student.name}</td>
                <td>{student.email}</td>
                <td><span className="course-tag">{student.course}</span></td>
                <td>
                  <div className="action-buttons">
                    <button
                      className="btn-edit"
                      onClick={() => onEdit(student)}
                    >
                      ✏️ Edit
                    </button>
                    <button
                      className="btn-delete"
                      onClick={() => onDelete(student.id)}
                    >
                      🗑️ Delete
                    </button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}

export default StudentList
