import vertx;
from core.event_bus import EventBus

def handler(msg):
   print 'received message %s ' % msg.body
   msg.reply('replied message from python' )

EventBus.register_handler('red.vertx.server.wellknown',
   handler = handler)

