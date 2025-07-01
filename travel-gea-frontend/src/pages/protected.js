import { useRouter } from "next/router"
import { useState, useEffect } from "react"

import fetchProtectedData from "@/utils/protectedFetch"

function ProtectedPage({ usersJson }) {
    const router = useRouter()

    const [users, setUsers] = useState(null)
    // const [error, setError] = useState(false)

    useEffect(() => {
        async function fetchUsers() {
            let resData = await fetchProtectedData(
                "http://localhost:9030/api/users/all"
            )
            if (resData) setUsers(resData.detail.users)
            else router.push("/login-or-signup")
        }
        fetchUsers()
    }, [router])

    if (users) {
        return (
            <>
                <h1 className="font-bold font-2xl">List of all users</h1>
                {users.map((user) => (
                    <div key={user.id}>{user.userName}</div>
                ))}
            </>
        )
    } else return <p>Loading ...</p>
}

export default ProtectedPage
