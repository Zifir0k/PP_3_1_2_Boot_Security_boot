function printUsersTable() {
    fetch(urlUsers).then(res => {
            res.json().then(data => {
                    let usersTableInfo = "";
                    data.forEach((user) => {
                        usersTableInfo += `   
                    <tr>
                        <td>${user["id"]}</td>
                        <td>${user["firstName"]}</td>
                        <td>${user["lastName"]}</td>
                        <td>${user["age"]}</td>
                        <td>${user["email"]}</td>
                        <td>${user["roles"].map(el => el.role)}</td>
                        <td><button class="btn btn-info" id="editButton">Edit</button></td>
                        <td><button class="btn btn-danger" id="deleteButton">Delete</button></td>                 
                    </tr>
                `
                    })
                    document.querySelector("#data").innerHTML = usersTableInfo;
                }
            )
        }
    )
}

printUsersTable()












