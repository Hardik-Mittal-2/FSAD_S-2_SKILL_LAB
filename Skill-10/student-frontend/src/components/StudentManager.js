import React, { useState, useEffect } from "react";
import axios from "axios";
import "../App.css";

function StudentManager() {

    const [students, setStudents] = useState([]);

    const [student, setStudent] = useState({
        id: "",
        name: "",
        course: ""
    });

    useEffect(() => {
        fetchStudents();
    }, []);

    const fetchStudents = () => {
        axios.get("http://localhost:8080/students")
            .then(res => setStudents(res.data));
    };

    const handleChange = (e) => {
        setStudent({
            ...student,
            [e.target.name]: e.target.value
        });
    };

    const addStudent = () => {
        axios.post("http://localhost:8080/students", student)
            .then(() => {
                fetchStudents();
                setStudent({ id: "", name: "", course: "" });
            });
    };

    const deleteStudent = (id) => {
        axios.delete(`http://localhost:8080/students/${id}`)
            .then(fetchStudents);
    };

    return (
        <div className="container">

            <h2>Student Manager</h2>

            <div className="form">

                <input
                    name="id"
                    placeholder="ID"
                    value={student.id}
                    onChange={handleChange}
                />

                <input
                    name="name"
                    placeholder="Name"
                    value={student.name}
                    onChange={handleChange}
                />

                <input
                    name="course"
                    placeholder="Course"
                    value={student.course}
                    onChange={handleChange}
                />

                <button className="addBtn" onClick={addStudent}>
                    Add Student
                </button>

            </div>

            <table>

                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Course</th>
                        <th>Action</th>
                    </tr>
                </thead>

                <tbody>

                    {students.map((s) => (
                        <tr key={s.id}>
                            <td>{s.id}</td>
                            <td>{s.name}</td>
                            <td>{s.course}</td>
                            <td>
                                <button
                                    className="deleteBtn"
                                    onClick={() => deleteStudent(s.id)}
                                >
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))}

                </tbody>

            </table>

        </div>
    );
}

export default StudentManager;