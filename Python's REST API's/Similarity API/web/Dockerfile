FROM python:3
WORKDIR /usr/src/app
COPY requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt
COPY . .
RUN pip install nltk
RUN pip install spacy==2.3.5
RUN pip install ./en_core_web_sm-2.3.1.tar.gz
RUN pip install pyresparser
CMD ["python", "app.py"]