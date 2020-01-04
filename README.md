# Setup a dev environment

1. Clone the whole project.
2. Move to the `introduction` directory and compile the project with Maven (`mvn clean install`) and build the dockerfile (`docker build -t recipe-intro .`).
3. Move to the `filter` directory and compile the project with Maven (`mvn clean install`) and build the dockerfile (`docker build -t recipe-filter .`).
4. Move to the `recipe-crud` directory and compile the project with Maven (`mvn clean install`), build the dockerfile (`docker build -t recipe-crud .`) and run docker compose with `docker-compose up -d` (to see logs type `docker-compose logs -f`).

