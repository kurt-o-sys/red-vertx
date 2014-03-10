<?php

Vertx::eventBus()->registerHandler('red.vertx.server.dirsearch',
   function($message) {
      $message->reply('pong');
   });

?>