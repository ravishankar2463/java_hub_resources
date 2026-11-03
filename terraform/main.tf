terraform {
  required_version = ">= 1.5.0"
  required_providers {
    kubernetes = {
      source  = "hashicorp/kubernetes"
      version = "~> 2.30"
    }
  }
}

variable "environment" {
  type        = string
  default     = "production"
  description = "Target deployment environment"
}

variable "replica_count" {
  type        = number
  default     = 3
  description = "Number of active pod replicas"
}

output "deployment_tier" {
  value       = var.environment
  description = "Target infrastructure tier"
}
