import { useRouter } from "next/router"

import LoginForm from "@/components/LoginForm"
import Link from "next/link"

function Login() {
    const router = useRouter()

    async function handleLoginSubmit(e) {
        e.preventDefault()

        const formData = new FormData(e.currentTarget)
        const username = formData.get("username")
        const password = formData.get("password")

        try {
            const res = await fetch("http://localhost:9030/login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ userName: username, password }),
            })
            if (res.ok) {
                const data = await res.json()
                localStorage.setItem("userData", JSON.stringify(data))
                localStorage.setItem("jwtToken", data.detail.token)
                router.push("/")
            } else {
                console.error("Could not login due to invalid credentials")
            }
        } catch (error) {
            console.error(error)
            console.error("Could not login due to error response from server")
        }
    }

    return (
        <>
            <div className="w-9/10 mx-auto">
                <h1 className="text-3xl font-bold">Login</h1>
                <p className="my-4 text-gray-600">
                    No account?{" "}
                    <Link href="/onboarding" className="underline">
                        Sign Up first.
                    </Link>
                </p>
                <LoginForm handleSubmit={handleLoginSubmit} />
            </div>
        </>
    )
}

export default Login
