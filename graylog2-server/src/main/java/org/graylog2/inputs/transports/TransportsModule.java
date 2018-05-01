/**
 * This file is part of Graylog.
 *
 * Graylog is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Graylog is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Graylog.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.graylog2.inputs.transports;

import com.google.inject.multibindings.MapBinder;
import io.netty.channel.EventLoopGroup;
import org.graylog2.inputs.transports.netty.EventLoopGroupFactory;
import org.graylog2.inputs.transports.netty.EventLoopGroupProvider;
import org.graylog2.plugin.inject.Graylog2Module;
import org.graylog2.plugin.inputs.transports.Transport;

public class TransportsModule extends Graylog2Module {
    @Override
    protected void configure() {
        final MapBinder<String, Transport.Factory<? extends Transport>> mapBinder = transportMapBinder();

        installTransport(mapBinder, "udp", UdpTransport.class);
        installTransport(mapBinder, "tcp", TcpTransport.class);
        installTransport(mapBinder, "http", HttpTransport.class);
        installTransport(mapBinder, "randomhttp", RandomMessageTransport.class);
        installTransport(mapBinder, "kafka", KafkaTransport.class);
        installTransport(mapBinder, "kafkanew", KafkaNewTransport.class);
        installTransport(mapBinder, "amqp", AmqpTransport.class);
        installTransport(mapBinder, "httppoll", HttpPollTransport.class);
        installTransport(mapBinder, "syslog-tcp", SyslogTcpTransport.class);

        bind(EventLoopGroupFactory.class).asEagerSingleton();
        bind(EventLoopGroup.class).toProvider(EventLoopGroupProvider.class).asEagerSingleton();
    }
}
