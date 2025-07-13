📘 What I Learned

Through this project, I gained hands-on experience in core backend development concepts and low-level network programming using Java. Key takeaways include:

    🌐 Understanding the HTTP Protocol
    Learned how HTTP servers handle requests and responses at the socket level, including parsing headers, handling different request types, and generating proper responses.

    🧠 Building a Custom HTTP Server in Java
    Implemented a lightweight, multithreaded HTTP server from scratch using raw TCP sockets, without external web frameworks.

    🔁 Parsing and Handling JSON Requests
    Used the Jackson library (ObjectMapper, JsonNode) to parse incoming JSON request bodies and construct structured JSON responses.

    🧵 Concurrency with Threads
    Handled multiple simultaneous client connections using a thread pool (Executor), ensuring efficient request processing.

    🛠️ Basic CRUD Logic & Input Validation
    Implemented minimal business logic to process HTTP methods and validate input data from clients.

    🧪 Clean Java Practices
    Utilized modern Java features such as var for type inference, lambda expressions, and the Stream API for cleaner, more readable code.

    📡 HTTP Status Codes & Structured Responses
    Learned to return proper HTTP response codes (200, 400, 404, etc.) along with well-structured headers and response bodies.

💭 Why I Built This

Before this project, I was unsure how the frontend and backend truly communicated. I knew HTTP was involved, but I didn’t understand how. To bridge that gap, I decided to build my own HTTP server from scratch.

In the process, I discovered how:

    The server reads raw bytes over a socket and translates them into meaningful requests.

    Threads manage concurrent connections.

    Streams and input parsing work under the hood in Java.

This project not only deepened my understanding of backend communication but also gave me real insight into how frameworks like Spring Boot 
