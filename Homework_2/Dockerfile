FROM python:3.9-slim
WORKDIR /app
RUN pip install "fastapi[all]"
COPY main.py .
EXPOSE 8000/tcp
CMD ["python", "main.py"]
