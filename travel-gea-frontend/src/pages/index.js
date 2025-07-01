import { useEffect, useState } from "react"

function Home() {
    const [user, setUser] = useState(null)

    useEffect(() => {
        const jsonData = localStorage.getItem("userData")
        if (jsonData) setUser(JSON.parse(jsonData))
    }, [])

    return (
        <div className="grid place-items-center h-full w-full">
            {user && (
                <span className="text-2xl font-bold">
                    Hello {user.detail.userName}
                </span>
            )}
        </div>
    )
}

export default Home
