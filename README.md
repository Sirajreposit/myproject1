# Cloud Services

## 4.2.1 Cloud Server Evaluation – Truelec Booking Application

### 4.2.1.1 Overview

Truelec currently operates six physical servers (three located at headquarters and one at each branch office). These servers are five-year-old Dell PowerEdge tower systems and require replacement. Two options were evaluated:

- Purchasing new physical servers
- Migrating the workload to cloud-based virtual machines

To support decision-making, cloud virtual machine pricing was analysed using the official pricing calculators from:

- Amazon Web Services (AWS)
- Microsoft Azure

Only one virtual machine configuration was priced as required by the project. This single VM represents a standard booking application server. The total infrastructure cost for six servers is calculated later in this section.

Exports from both cloud calculators have been included as evidence in the project repository.

### 4.2.1.2 Selected Cloud Providers

The following cloud service providers (CSPs) were evaluated:

| Cloud Provider | Service Type | Region Used | Pricing Tool |
|---|---|---|---|
| Amazon Web Services (AWS) | EC2 Virtual Machine | Asia Pacific (Sydney) | AWS Pricing Calculator |
| Microsoft Azure | Virtual Machines | Australia East | Azure Pricing Calculator |

Both regions were selected to ensure:

- Low latency for Australian users
- Data residency compliance
- Fair comparison between providers

### 4.2.1.3 VM Specification Selection and Justification

The same or similar specifications were chosen across providers to maintain a fair comparison.

#### Selected Virtual Machine Specifications

| Specification | Selected Value | Justification |
|---|---|---|
| Operating System | Linux | Lower licensing cost compared to Windows and suitable for web applications |
| vCPU | 2 vCPUs | Adequate processing power for a booking application web server |
| Memory | 8 GB RAM | Required to handle database queries and concurrent users |
| Storage | Managed SSD (~128 GB equivalent) | Provides reliable performance and fast read/write speeds |
| Pricing Model | Pay-As-You-Go | Flexible for comparison without long-term commitment |
| Usage | 730 hours/month | Represents continuous production operation |

These specifications were selected because the booking application requires moderate compute resources rather than high-performance database infrastructure. Choosing identical CPU and RAM across providers ensures pricing comparisons are fair and unbiased.

### 4.2.1.4 Cloud VM Pricing Comparison

Pricing was obtained directly from official cloud calculators and exported as evidence files stored in the project repository.

#### Single VM Monthly and Annual Cost

| Provider | VM Type | Monthly Cost | Annual Cost (12 months) |
|---|---|---|---|
| Microsoft Azure | D2 v3 (2 vCPU, 8GB RAM) | A$143.85 | A$1,726.20 |
| AWS EC2 | m6i.large equivalent | $224.35 USD | $2,692.20 USD |

**Calculation:** Annual Cost = Monthly Cost × 12

So:
- A$143.85 × 12 = A$1,726.20

**Figure 4.2.1-A – Azure Pricing Calculator Configuration**
<img width="1918" height="1015" alt="Azure-1" src="https://github.com/user-attachments/assets/54a80b2b-41b9-4c03-8288-dc8dda309b90" />
<img width="866" height="218" alt="image" src="https://github.com/user-attachments/assets/c07aca57-5734-43be-b680-8c81fc4655ca" />
**Figure 4.2.1-B – AWS Pricing Calculator Configuration**
<img width="1918" height="1018" alt="aws-1" src="https://github.com/user-attachments/assets/8f98b38a-f3c0-43d3-b118-af967f825110" />
<img width="1914" height="628" alt="image" src="https://github.com/user-attachments/assets/9c034ea7-3a62-489f-b7b3-fcab1f4cf54d" />


#### Five-Year Cost Projection for All Servers

Truelec requires six servers in total:

- 3 × Headquarters
- 3 × Branch Offices

**Total Annual Cost for 6 Servers**

| Provider | Annual Cost per VM | Total Annual Cost (6 VMs) |
|---|---|---|
| Azure | A$1,726.20 | A$10,357.20 |
| AWS | $2,692.20 USD | $16,153.20 USD |

**Five-Year Cost Estimate**

| Provider | Five-Year Cost |
|---|---|
| Azure | A$51,786.00 |
| AWS | $80,766.00 USD |

### 4.2.1.7 Cloud Provider Recommendation

Based on the pricing comparison and configuration analysis, **Microsoft Azure** is recommended for Truelec.

**Reasons:**

1. **Lower Monthly and Annual Cost** – Azure provides a significantly lower price for an equivalent VM configuration.
2. **Regional Optimisation** – Azure Australia East region aligns well with local enterprise deployments and offers low latency.
3. **Comparable Specifications** – Both AWS and Azure provide similar CPU, RAM, and storage performance, meaning Azure delivers better cost efficiency without sacrificing capability.

### 4.2.1.8 Advantages and Disadvantages of Cloud VMs

#### Advantages

- No upfront hardware purchase cost
- Automatic scalability
- Built-in redundancy and high availability
- Reduced maintenance overhead
- Faster deployment compared to physical servers

#### Disadvantages

- Long-term operational cost may exceed on-premise hardware
- Dependence on internet connectivity
- Potential vendor lock-in
- Ongoing subscription expenses

### 4.2.1.9 Conclusion

The analysis demonstrates that cloud virtual machines provide a flexible and scalable alternative to replacing Truelec's aging physical infrastructure. Both AWS and Azure offer suitable VM configurations; however, Azure presents a more cost-effective solution while maintaining equivalent technical specifications. Therefore, Azure is recommended as the preferred cloud provider for migrating the booking application servers.
