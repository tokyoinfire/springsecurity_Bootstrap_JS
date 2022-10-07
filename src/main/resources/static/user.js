fetch('http://localhost:8080/rest')   //user
    .then(res => { res.json().then(
        user=>{
            let navBarUser = ""
            navBarUser += "<b class=\"text-white\" style=\"font-size: 20px\">"+user.email+"</b>"
            navBarUser += "<span class=\"text-white\"  style=\"font-size: 18px\"> with roles: </span>"
            navBarUser += "<span class=\"text-white\"  style=\"font-size: 20px\">"
            user.roles.forEach((role) => navBarUser += role.role.replace('ROLE_','')+' ')
            navBarUser += "</span>"
            document.getElementById("navBarUser").innerHTML = navBarUser

            let tableUser = ""
            tableUser += "<tr>"
            tableUser += "<td>"+user.id+"</td>"
            tableUser += "<td>"+user.name+"</td>"
            tableUser += "<td>"+user.surname+"</td>"
            tableUser += "<td>"+user.email+"</td>"
            tableUser += "<td>"
            user.roles.forEach((role) => tableUser += role.role.replace('ROLE_','')+" ")
            tableUser += "</td>"
            document.getElementById("userInfo").innerHTML = tableUser
        }
    )})