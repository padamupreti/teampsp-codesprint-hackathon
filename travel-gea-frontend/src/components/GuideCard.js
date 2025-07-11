function GuideCard() {
    return (
        <div className="flex space-x-4 border-2 border-gray-200 rounded-lg p-2">
            <img
                src="/tour-guide.jpg"
                alt="tour-guide"
                className="h-48 w-auto block"
            />
            <div className="flex flex-col gap-2">
                <p className="text-xl font-bold">Ram Thapa</p>
                <p className="text-gray-600 my-2">
                    Hello. I am Ram Thapa and can be your local guide.
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
                            d="M18.427 14.768 17.2 13.542a1.733 1.733 0 0 0-2.45 0l-.613.613a1.732 1.732 0 0 1-2.45 0l-1.838-1.84a1.735 1.735 0 0 1 0-2.452l.612-.613a1.735 1.735 0 0 0 0-2.452L9.237 5.572a1.6 1.6 0 0 0-2.45 0c-3.223 3.2-1.702 6.896 1.519 10.117 3.22 3.221 6.914 4.745 10.12 1.535a1.601 1.601 0 0 0 0-2.456Z"
                        />
                    </svg>
                    9849772244
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
                    Kathmandu Municipality
                </p>
                <div className="flex gap-3 mt-2">
                    <span className="bg-gray-200/80 rounded-full p-2">
                        Adventure
                    </span>
                    <span className="bg-gray-200/80 rounded-full p-2">
                        Nature
                    </span>
                    <span className="bg-gray-200/80 rounded-full p-2">
                        Social/Cultural
                    </span>
                </div>
                <button className="cursor-pointer text-white bg-gray-800 hover:bg-gray-900 focus:outline-none focus:ring-4 focus:ring-gray-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-gray-800 dark:hover:bg-gray-700 dark:focus:ring-gray-700 dark:border-gray-700 mt-3 object-contain">
                    Reserve Guide
                </button>
            </div>
        </div>
    )
}

export default GuideCard
