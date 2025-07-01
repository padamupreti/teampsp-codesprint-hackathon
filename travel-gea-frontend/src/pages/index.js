import EventCard from "@/components/EventCard"
import GuideCard from "@/components/GuideCard"
import { useEffect, useState } from "react"

function Home() {
    const [user, setUser] = useState(null)

    useEffect(() => {
        const jsonData = localStorage.getItem("userData")
        if (jsonData) setUser(JSON.parse(jsonData))
    }, [])

    return (
        <>
            <div className="">
                {user && (
                    <span className="text-2xl font-bold">
                        Hello {user.detail.userName}
                    </span>
                )}
            </div>
            <h2 className="font-bold text-2xl mb-4">Guides</h2>
            <div className="grid grid-cols-3 gap-4 mb-4">
                <GuideCard />
                <GuideCard />
                <GuideCard />
            </div>
            <h2 className="font-bold text-2xl mb-4">Events</h2>
            <div className="grid grid-cols-3 gap-4 mb-4">
                <EventCard />
                <EventCard />
                <EventCard />
            </div>
        </>
    )
}

export default Home
