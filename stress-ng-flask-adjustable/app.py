from flask import Flask, request
import math
from concurrent.futures import ProcessPoolExecutor

app = Flask(__name__)

# Process pool to handle multiple workers
executor = ProcessPoolExecutor()

@app.route('/')
def hello_world():
    return 'Hello, Flask with configurable stress workers!'

@app.route('/stress')
def stress_cpu():
    # Get the number of workers and workload size from query parameters
    num_workers = int(request.args.get('workers', 4))  # Default to 4 workers
    workload_size = int(request.args.get('workload', 100000))  # Default workload size
    
    # Submit tasks to the executor (one task per worker)
    futures = [executor.submit(cpu_stress_workload, workload_size) for _ in range(num_workers)]
    
    # Wait for all workers to complete and sum their results
    total_result = sum(f.result() for f in futures)
    
    return f"CPU stress test completed with {num_workers} workers. Workload: {workload_size}. Total Result: {total_result}"

def cpu_stress_workload(n):
    """CPU-bound task to simulate stress by calculating square roots."""
    return sum(math.sqrt(i) for i in range(n))

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=8080)

