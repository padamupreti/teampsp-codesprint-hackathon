import { useEffect } from "react"
import { useRouter } from "next/router"

function Logout() {
    const router = useRouter()

    useEffect(() => {
        localStorage.setItem("jwtToken", "")
        localStorage.setItem("userData", "")
        router.push("/login")
    }, [router])

    return <></>
}

export default Logout
