from flask import Flask
import instana
app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello, Flask running in Docker!'

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=8080)

