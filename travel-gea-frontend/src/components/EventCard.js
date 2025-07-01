function EventCard() {
    return (
        <div className="flex flex-col gap-2 border-2 border-gray-200 rounded-lg p-2">
            <div>
                <p className="text-xl font-bold">Music Concert</p>
                <p className="text-sm">
                    Organized by: <span className="underline">Shiva Group</span>
                </p>
            </div>
            <p className="text-gray-600 my-2">
                Concert by famous band Nepathya.
            </p>
            <p className="text-gray-600 flex gap-1">
                <svg
                    className="w-6 h-6"
                    aria-hidden="true"
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    fill="none"
                    viewBox="0 0 24 24"
                >
                    <path
                        stroke="currentColor"
                        strokeLinecap="round"
                        strokeLinejoin="round"
                        strokeWidth="2"
                        d="M12 8v4l3 3m6-3a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z"
                    />
                </svg>
                Tuesday, Jul 01, 2025 (8 AM - 6 PM)
            </p>
            <p className="text-gray-600 flex gap-1">
                <svg
                    className="w-6 h-6"
                    aria-hidden="true"
                    xmlns="http://www.w3.org/2000/svg"
                    width="24"
                    height="24"
                    fill="none"
                    viewBox="0 0 24 24"
                >
                    <path
                        stroke="currentColor"
                        strokeLinecap="round"
                        strokeLinejoin="round"
                        strokeWidth="2"
                        d="M12 13a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"
                    />
                    <path
                        stroke="currentColor"
                        strokeLinecap="round"
                        strokeLinejoin="round"
                        strokeWidth="2"
                        d="M17.8 13.938h-.011a7 7 0 1 0-11.464.144h-.016l.14.171c.1.127.2.251.3.371L12 21l5.13-6.248c.194-.209.374-.429.54-.659l.13-.155Z"
                    />
                </svg>
                Tundikhel
            </p>
        </div>
    )
}

export default EventCard
