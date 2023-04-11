const deleteModal = new bootstrap.Modal(document.querySelector("#deleteModal"))

const userIdDelete = document.querySelector("#idDelete")

on(document, "click", ".btn-danger", e => {
    const currentUserId = e.target.parentNode.parentNode.firstElementChild.innerHTML
    sendRequest("GET", "/api/users/".concat(currentUserId))
        .then(data => {
            userIdDelete.value = data["id"]
            document.querySelector("#firstNameDelete").value = data["firstName"]
            document.querySelector("#lastNameDelete").value = data["lastName"]
            document.querySelector("#ageDelete").value = data["age"]
            document.querySelector("#emailDelete").value = data["email"]

            const userRolesDelete = data["roles"].map(role => role["role"])

            let selectOptionRoles = ``
            sendRequest("GET", "/api/roles")
                .then(data => {
                        let allRoles = data.map(el => el["role"])
                        for (const role of allRoles) {
                            if (userRolesDelete.includes(role)) {
                                selectOptionRoles += `<option value="${role}" selected>${role}</option>`
                            } else {
                                selectOptionRoles += `<option value="${role}">${role}</option>`
                            }
                        }
                        document.querySelector("#rolesDelete").innerHTML = selectOptionRoles
                    }
                )
            deleteModal.show()
        })
})

document.querySelector("#deleteForm").addEventListener("submit", (e) => {
        e.preventDefault()

        fetch(urlUsers + userIdDelete.value, {
            method: "DELETE",
            headers: {"Content-type": "application/json"},
            body: JSON.stringify({})
        }).then(() => {
            deleteModal.hide()
            printUsersTable()
        })
    }
)
