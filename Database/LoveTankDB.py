from redis import Redis

pool = Redis.connection(host="localhost", port=6379, db=0)
redis = Redis.Redis(connection_pool=pool)

redis.set('mykey', 'hello from pyhton!')

value = redis.get('mykey')

print(value)

