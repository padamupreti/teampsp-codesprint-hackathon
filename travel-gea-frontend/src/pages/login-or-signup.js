import { useRouter } from "next/router"

import LoginForm from "@/components/LoginForm"
import SignUpForm from "@/components/SignUpForm"

function LoginOrSignUP() {
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

    async function handleSignUpSubmit(e) {
        e.preventDefault()

        const formData = new FormData(e.currentTarget)
        const email = formData.get("email")
        const username = formData.get("username")
        const password = formData.get("password")

        try {
            const res = await fetch("http://localhost:9030/register", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    email,
                    userName: username,
                    password,
                    roleId: 1,
                }),
            })

            if (res.ok) {
                const data = await res.json()
                console.log(data)
                console.log("Sign Up successful. Please login now.")
            } else {
                console.error("Could not sign up due to invalid credentials")
            }
        } catch (error) {
            console.error(error)
            console.error("Could not sign up due to error response from server")
        }
    }

    return (
        <>
            <div className="flex flex-col sm:flex-row sm:gap-12 mx-6 mt-4">
                <div>
                    <h1 className="text-2xl font-bold mb-5">Login</h1>
                    <LoginForm handleSubmit={handleLoginSubmit} />
                </div>
                <hr className="sm:hidden border-0 h-px bg-slate-900/10 my-5 w-full" />
                <div>
                    <h1 className="text-2xl font-bold mb-5">Sign Up</h1>
                    <SignUpForm handleSubmit={handleSignUpSubmit} />
                </div>
            </div>
        </>
    )
}

export default LoginOrSignUP
