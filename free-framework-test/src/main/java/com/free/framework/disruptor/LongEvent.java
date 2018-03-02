package com.free.framework.disruptor;

import lombok.*;

/**
 * com.free.framework.disruptor.LongEvent
 * 传递数据
 * @author lipeng
 * @dateTime 2018/3/2 16:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LongEvent {

    private Long value;

}
