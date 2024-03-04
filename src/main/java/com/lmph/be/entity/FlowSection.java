package com.lmph.be.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Entity object for Flow's Section
 *
 * @author Jeffrey John Javison
 * @since 06-Dec-2023
 */
@Data
@Entity
@Table(name="flow_section")
public class FlowSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "section_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Section section;

    @JoinColumn(name = "flow_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Flow flow;

    @Column(name = "flow_id", insertable = false, updatable = false)
    private Long flowId;

    private Integer sortOrder;

    @Override
    public String toString() {
        return "FlowSection{" +
                "id=" + id +
                ", section=" + section +
                ", flow=" + flow +
                ", flowId=" + flowId +
                ", sortOrder=" + sortOrder +
                '}';
    }
}
