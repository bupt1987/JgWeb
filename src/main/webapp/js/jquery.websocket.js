(function($){
    $.extend({
        websocket: function(url, s, protocols) {
            var ws;
            if ( protocols ) {
                ws = window['MozWebSocket'] ? new MozWebSocket(url, protocols) : window['WebSocket'] ? new WebSocket(url, protocols) : null;
            } else {
                ws = window['MozWebSocket'] ? new MozWebSocket(url) : window['WebSocket'] ? new WebSocket(url) : null;
            }

            var settings = {
                open: function(){},
                close: function(){},
                message: function(){},
                error: function(){},
                options: {},
                events: {}
            };
            $.extend(settings, $.websocketSettings, s);

            if (ws) {
                $(ws)
                    .bind('open', settings.open)
                    .bind('close', settings.close)
                    .bind('message', settings.message)
                    .bind('error', settings.error)
                    .bind('message', function(e) {
                        var m = $.evalJSON(e.originalEvent.data);
                        console.log(m);
                        var h = settings.events[m.h];
                        if (h) h.call(this, m);
                    });
                ws._send = ws.send;
                ws.send = function(h, p) {
                    var m = {h: h};
                    m = $.extend(true, m, $.extend(true, {}, settings.options, m));
                    if (p) m['p'] = p;
                    return this._send($.toJSON(m));
                };
                $(window).unload(function(){ ws.close(); ws = null; });
            }

            return ws;
        }
    });
})(jQuery);