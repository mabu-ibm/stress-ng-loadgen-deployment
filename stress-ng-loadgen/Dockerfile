# Verwende das offizielle Python Image als Basis
FROM python:3.9-slim

# Setze das Arbeitsverzeichnis im Container
WORKDIR /app

# Kopiere die `requirements.txt` in den Container
COPY requirements.txt .

# Installiere die Abhängigkeiten
RUN pip install --no-cache-dir -r requirements.txt

# Kopiere den Rest der Applikation
COPY . .

# Exponiere den Port 8080
EXPOSE 8080

# Starte die Flask-Anwendung
CMD ["python", "app.py"]


