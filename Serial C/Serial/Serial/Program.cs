using System;
using System.IO.Ports;

namespace Serial
{
    internal class Program
    {
        public static void Main(string[] args)
        {
            SerialPort serialPort = new SerialPort("COM1", 9600);
            serialPort.Open();
            Console.WriteLine("Esperando datos desde el puerto serial...");
            serialPort.DataReceived += receptor;
            Console.ReadKey();
            serialPort.Close();
        }
        
        static void receptor(object sender, SerialDataReceivedEventArgs e)
        {
            SerialPort serialPort = (SerialPort)sender;
            string data = serialPort.ReadExisting();
            Console.WriteLine("Datos recibidos: " + data);
            serialPort.Write("Mensaje recibido correctamente.");
        }
    }
}