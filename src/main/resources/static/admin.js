let table = ""
const showTable = (users) => {   //admin
    users.forEach((user) => {
        table += `
                <tr id="${user.id}">
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.email}</td>
                    <td>`

        user.roles.forEach((role) => table += role.role.replace('ROLE_', '') + " ")
        table += `
                    </td>
                    <td><button class="btn btn-info eBtn" data-toggle="modal">Edit</button></td>
                    <td><button class="btn btn-danger dBtn" data-toggle="modal">Delete</button></td>
                 `
    })
    document.getElementById("tableAllUsers").innerHTML = table
}

fetch("http://localhost:8080/users/user")     //nav
    .then(res => {
        res.json().then(
            user => {
                let navBar = ""
                navBar += "<b class=\"text-white\"  style=\"font-size: 20px\">" + user.email + "</b>"
                navBar += "<span class=\"text-white\"  style=\"font-size: 18px\"> with roles: </span>"
                navBar += "<span class=\"text-white\"  style=\"font-size: 20px\">"
                user.roles.forEach((role) => navBar += role.role.replace('ROLE_', '') + ' ')
                navBar += "</span>"
                document.getElementById("navBar").innerHTML = navBar
            }
        )
    })


fetch('http://localhost:8080/users')
    .then(response => response.json())
    .then(data => showTable(data))

const on = (element, event, selector, handler) => {
    element.addEventListener(event, e => {
        if (e.target.closest(selector)) {
            handler(e)
        }
    })
}


on(document, 'click', '.eBtn', e => {  //edit

    const line = e.target.parentNode.parentNode
    const idMod = line.children[0].innerHTML
    const nameMod = line.children[1].innerHTML
    const surnameMod = line.children[2].innerHTML
    const emailMod = line.children[3].innerHTML


    Id.value = idMod
    Name.value = nameMod
    Surname.value = surnameMod
    emailEdit.value = emailMod
    $('#editModal').modal()
})

editModal.addEventListener('submit', (e) => {
    e.preventDefault()
    let id = 0
    let rolesListEdit = [];
    for (let i = 0; i < $('#rolesEdit').val().length; i++) {
        if ($('#rolesEdit').val()[i] === 'ROLE_ADMIN') {
            id = 1
        } else {
            id = 2
        }
        rolesListEdit[i] = {id: id, role: $('#rolesEdit').val()[i]};
    }
    let editUser = {
        id: Id.value,
        name: Name.value,
        surname: Surname.value,
        email: emailEdit.value,
        password: passEdit.value,
        roles: rolesListEdit

    }
    fetch('http://localhost:8080/users', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(editUser)
    })
        .then(response => response.json())
        .then(data => {
            const editUserInTable = []
            editUserInTable.push(data)
            showTable(editUserInTable)
        })
        .then(() => document.getElementById(Id.value).remove())
        .then(() => document.getElementById('editModalClose').click())

})


on(document, 'click', '.dBtn', e => {  //delete

    const line = e.target.parentNode.parentNode
    const idDelMod = line.children[0].innerHTML
    const nameDelMod = line.children[1].innerHTML
    const surNameDelMod = line.children[2].innerHTML
    const emailDelMod = line.children[3].innerHTML

    idDel.value = idDelMod
    nameDel.value = nameDelMod
    surnameDel.value = surNameDelMod
    emailDel.value = emailDelMod

    $('#deleteModal').modal()
})

deleteModal.addEventListener('submit', (e) => {
    e.preventDefault()
    fetch('http://localhost:8080/users/' + idDel.value, {
        method: 'DELETE'
    })
        // .then(() => document.getElementById(idDelete.value).remove())
        .then(() => document.getElementById(idDel.value).remove())
        .then(() => document.getElementById('deleteModalClose').click())
})

newUserForm.addEventListener('submit', (e) => {   //newu
    e.preventDefault()
    let id = 0
    let rolesList = [];
    for (let i = 0; i < $('#roles').val().length; i++) {
        if ($('#roles').val()[i] === 'ROLE_ADMIN') {
            id = 1
        } else {
            id = 2
        }
        rolesList[i] = {id: id, role: $('#roles').val()[i]};
    }
    let newUser = {
        name: nameNew.value,
        surname: surnameNew.value,
        email: emailNew.value,
        password: passNew.value,
        roles: rolesList


    }

    fetch('http://localhost:8080/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newUser)
    })
        .then(response => response.json())
        .then((data) => {
            const newUserInTable = []
            newUserInTable.push(data)
            showTable(newUserInTable)
        })
        .then(() => document.getElementById('userTable').click())
})
