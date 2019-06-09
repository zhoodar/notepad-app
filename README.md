#  Note Pad
This is a simple CRUD, SPA application

### Technologies used
* Java 1.8
* Spring Boot
* Angular 8
* Angular Material

### Prerequisites
* Java 1.8+
* npm (Node 10.10.0+)
* Angular CLI latest version
* Docker 17+
* Docker Compose version 1.18+
## How To Run
Make sure you have installed all the prerequisites
* Clone this project
```sh
git clone https://github.com/zhoodar/...
```
All the build bundles already within the source code, if you want to just start,
feel free to run
* Build frontend
```sh
cd <cloned project directory/notepad_frontend>
npm i
ng build --prod
```
* Build backend
```sh
cd <cloned project directory/notepad_backend>
./gradlew build
```
* Run Application
```sh
cd <cloned project directory/>
docker-compose up
```
### Try it
- Now open your browser: http://localhost:4200/

### Configuration
The App will run with default setting, in order to adjust setting see
`.env` file
