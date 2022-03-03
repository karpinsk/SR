import socket

serverIP = "127.0.0.1"
serverPort = 9008
number_to_send = 300

msg_bytes = (number_to_send).to_bytes(4, byteorder='big')

print('PYTHON UDP CLIENT')
client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
client.sendto(msg_bytes, (serverIP, serverPort))

buff, address = client.recvfrom(1024)
print("Received message from server: ")
print(int.from_bytes(buff, byteorder='big'))




