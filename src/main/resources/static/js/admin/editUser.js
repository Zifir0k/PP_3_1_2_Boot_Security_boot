const editModal = new bootstrap.Modal(document.querySelector("#editModal"))

const userId = document.querySelector("#idEdit")
const userFirstName = document.querySelector("#firstNameEdit")
const userLastName = document.querySelector("#lastNameEdit")
const userAge = document.querySelector("#ageEdit")
const userEmail = document.querySelector("#emailEdit")
const userPassword = document.querySelector("#passwordEdit")

on(document, "click", ".btn-info", e => {
    sendRequest('GET', "/api/users/".concat(e.target.parentNode.parentNode.firstElementChild.innerHTML))
        .then(data => {
                userId.value = data["id"]
                userFirstName.value = data["firstName"]
                userLastName.value = data["lastName"]
                userAge.value = data["age"]
                userEmail.value = data["email"]
                userPassword.value = data["password"]

                const userRolesEdit = data["roles"].map(role => role["role"])

                let selectOptionRoles = ``
                sendRequest('GET', "/api/roles")
                    .then(data => {
                            let allRoles = data.map(el => el["role"])
                            for (const role of allRoles) {
                                if (userRolesEdit.includes(role)) {
                                    selectOptionRoles += `<option value="${role}" selected>${role}</option>`
                                } else {
                                    selectOptionRoles += `<option value="${role}">${role}</option>`
                                }
                            }
                            document.querySelector("#rolesEdit").innerHTML = selectOptionRoles
                        }
                    )
                editModal.show()
            }
        )
})


document.querySelector("#editForm").addEventListener("submit", (e) => {
        e.preventDefault()

        let role = [...document.querySelector("#rolesEdit")]
            .filter(option => option.selected)
            .map(option => option.value)

        fetch(urlUsers, {
            method: 'PUT',
            headers: {'Content-type': 'application/json'},
            body: JSON.stringify({
                id: userId.value,
                firstName: userFirstName.value,
                lastName: userLastName.value,
                age: userAge.value,
                email: userEmail.value,
                password: userPassword.value,
                roles: role
            })
        }).then(() => {
            editModal.hide()
            printUsersTable()
            adminInfo()
        })
    }
)