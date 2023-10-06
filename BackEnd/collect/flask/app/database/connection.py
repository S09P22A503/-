from sqlalchemy import create_engine
from sqlalchemy.pool import QueuePool


engine = None


def setup_db(app):
    
    global engine

    DATABASE_URL = app.config.get('DATABASE_URL')
    DATABASE_PASSWORD = app.config.get('DATABASE_PASSWORD')

    if not DATABASE_URL:
        raise ValueError("No DATABASE URL found in the app configuration.")
    
    engine = create_engine(DATABASE_URL,poolclass=QueuePool,echo=True)


def get_connection():
    
    global engine
    
    if engine is None:
        raise ValueError("Database must be initialized.")
    
    connection = engine.connect()
    print(connection)
    return connection