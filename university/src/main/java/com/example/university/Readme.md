Students API

      1) GET -- /students: get all students
      
      2) GET -- /students/{id}: find student by id
      
      3) POST -- /students: post a new student and return this new record

            RequestBody: {
                        "name": xxxx
                    }
      
      4) PUT -- /students/{id}: update a existed student info and return this new record

            RequestBody: {
                        "name": xxxx
                    }
      
      5) DELETE -- /students/{id}: delete a student
      

Teacher API

      1) GET -- /teachers: get all teachers
      
      2) GET -- /teachers/{id}: find teacher by id
      
      3) POST -- /teachers: post a new teacher and return this new record

            RequestBody: {
                            "name": xxxx
                    }
      
      4) PUT -- /teachers/{id}: update a existed teacher info and return this new record

            RequestBody: {
                        "name": xxxx
                    }
      
      5) DELETE -- /teachers/{id}: delete a teacher
      

Teacher_Student API

      1) GET -- /teacher_students: get all teachers
      
      2) GET -- /teacher_students?teacher=id: find by teacher id
      
      3) GET -- /teacher_students?student=id: find by student id
      
      4) GET -- /teacher_students?teacher=id&student=id: find by teacher id and student id

      5) POST -- /teacher_students: 

            RequestBody: {
                    "teacher": {
                        "id": "2",
                        "name": "Mr. White"
                    },
                    "student": {
                        "id": "2",
                        "name": "Tim"
                    }
                }

      6) PUT -- /teacher_students/{id}: update a existed relation by id

            RequestBody: {
                    "teacher": {
                        "id": "2",
                        "name": "Mr. White"
                    },
                    "student": {
                        "id": "2",
                        "name": "Tim"
                    }
                }
      
      7) DELETE -- /teacher_students/{id}: delete a teacher_student relation by id
       
      
      
      
      
      
     
